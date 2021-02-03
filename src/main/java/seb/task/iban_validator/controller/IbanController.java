package seb.task.iban_validator.controller;

import org.springframework.web.bind.annotation.*;
import seb.task.iban_validator.model.Iban;
import seb.task.iban_validator.model.IbanDataWrapper;
import seb.task.iban_validator.service.IbanService;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class IbanController {

    private final IbanService ibanService;

    public IbanController(IbanService ibanService) {
        this.ibanService = ibanService;
    }

    @GetMapping("/ibanlist")
    public IbanDataWrapper ibanList() {
        Iban iban = new Iban();
        iban.setIban("LT647044001231465456");
        Iban iban1 = new Iban();
        iban1.setIban("LT487300010116913583");
        Iban iban2 = new Iban();
        iban2.setIban("LT784010051001391718");
        Iban iban3 = new Iban();
        iban3.setIban("LT517044077788877777");
        Iban iban4 = new Iban();
        iban4.setIban("CC051245445454552117989");
        IbanDataWrapper ibanWrapper = new IbanDataWrapper();
        ibanWrapper.setIbanList(Arrays.asList(iban, iban1, iban2, iban3, iban4));
        return ibanWrapper;
    }

    @PostMapping("/validate/{iban}")
    public boolean ibanValidator(@PathVariable("iban") String iban) {
        return ibanService.validate(iban);
    }

    @PostMapping("/validate")
    public IbanDataWrapper ibanListValidator(@RequestBody IbanDataWrapper wrapper) {
        wrapper
                .getIbanList()
                .stream()
                .filter(iban -> ibanService.validate(iban.getIban()))
                .forEach(iban -> iban.setBank(ibanService.containsString(iban.getIban())));
        return wrapper;
    }
}
