package pl.pjatk.jaz_26974_nbp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.pjatk.jaz_26974_nbp.exception.InvalidRequestException;
import pl.pjatk.jaz_26974_nbp.model.NBPResponse;
import pl.pjatk.jaz_26974_nbp.model.NBPRate;
import pl.pjatk.jaz_26974_nbp.model.Request;
import pl.pjatk.jaz_26974_nbp.repository.RequestRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RequestService {
    private final RequestRepository repository;
    private final RestTemplate restTemplate;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final String nbpUrl = "https://api.nbp.pl/api/exchangerates/rates/A/";

    public RequestService(RequestRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public Request getRate(String currency, String startDate, String endDate) {
        LocalDate start;
        LocalDate end;
        try {
            start = LocalDate.parse(startDate, dateFormat);
            end = LocalDate.parse(endDate, dateFormat);
        } catch (Exception e) {
            throw new InvalidRequestException("Invalid request data.");
        }
        NBPResponse response =
                restTemplate.getForObject(nbpUrl + currency + "/" + startDate + "/" + endDate, NBPResponse.class);
        Request request = new Request(currency, start, end, calculateMean(response), LocalDateTime.now());
        return repository.save(request);
    }

    private BigDecimal calculateMean(NBPResponse response) {
        BigDecimal sum = new BigDecimal(0);
        for (NBPRate rate : response.getRates()) {
            sum = sum.add(rate.getMid());
        }
        BigDecimal result = sum.divide(new BigDecimal(response.getRates().size()), RoundingMode.HALF_DOWN);
        return result.setScale(2, RoundingMode.HALF_DOWN);
    }
}
