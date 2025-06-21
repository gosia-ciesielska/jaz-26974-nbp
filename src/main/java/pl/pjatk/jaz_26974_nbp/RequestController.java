package pl.pjatk.jaz_26974_nbp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.jaz_26974_nbp.model.Request;
import pl.pjatk.jaz_26974_nbp.service.RequestService;

@RestController
@RequestMapping("/nbp")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @Operation(summary = "Get mean rate", description = "Returns mean rate for given currency in given period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rate successfully retrieved",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Request.class)))}),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Rates for given data not found",
                    content = @Content)
    })
    @Tag(name = "Get mean rate")
    @GetMapping("{currency}")
    public ResponseEntity<Request> getData(@PathVariable @Parameter(name = "currency", description = "Currency code", example = "USD") String currency,
                                           @RequestParam @Parameter(name = "start", description = "Start date for calculations", example = "2025-06-16") String start,
                                           @RequestParam @Parameter(name = "end", description = "End date for calculations", example = "2025-06-21") String end) {
        return ResponseEntity.ok(requestService.getRate(currency, start, end));
    }
}
