package pl.pjatk.jaz_26974_nbp.model;

import java.util.List;

public class NBPResponse {
    private String table;
    private String currency;
    private String code;
    private List<NBPRate> rates;

    public NBPResponse() {
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<NBPRate> getRates() {
        return rates;
    }

    public void setRates(List<NBPRate> rates) {
        this.rates = rates;
    }
}
