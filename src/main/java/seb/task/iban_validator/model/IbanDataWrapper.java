package seb.task.iban_validator.model;

import lombok.Data;
import java.util.List;

@Data
public class IbanDataWrapper {

    private List<Iban> ibanList;

}
