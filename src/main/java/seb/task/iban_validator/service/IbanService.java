package seb.task.iban_validator.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.regex.Pattern;

@Service
public class IbanService {

    private static final String checkDigitsMod = "97";
    private static final String SWEDBANK = "73000";
    private static final String LUMINOR = "40100";
    private static final String SEB = "70440";

    private static String normalize(String iban) {
        return iban.trim().replace(" ", "").toUpperCase();
    }

    private String charConverter(String prefix) {
        StringBuilder converted = new StringBuilder(2);
        for (int i = 0; i < prefix.length(); i++)
            converted.append(Character.toUpperCase(prefix.charAt(i)) - 55);
        return converted.toString();
    }

    private boolean ibanCheckSum(String iban) {
        String ibanModified =
                iban.substring(4) + charConverter(iban.substring(0, 2)) + iban.substring(2, 4);
        BigInteger ibanInt = new BigInteger(ibanModified);
        BigInteger dal = new BigInteger(checkDigitsMod);
        return ibanInt.mod(dal).compareTo(BigInteger.ONE) == 0;
    }

    private boolean validatePattern(String iban) {
        return IBAN_PATTERN.matcher(iban).matches() && ibanCheckSum(iban);
    }

    public String containsString(String iban) {
        String ibanModified = iban.substring(4, 9);
        if (SWEDBANK.contains(ibanModified)) {
            return "SWEDBANK";
        } else if (SEB.contains(ibanModified)) {
            return "SEB";
        } else if (LUMINOR.contains(ibanModified)) {
            return "LUMINOR";
        } else return ";";
    }

    public boolean validate(String iban) {
        return validatePattern(normalize(iban));
    }

    private static final Pattern IBAN_PATTERN = Pattern.compile("" +
            "^AL\\d{10}[0-9A-Z]{16}$|" +
            "^AD\\d{10}[0-9A-Z]{12}$|" +
            "^AT\\d{18}$|" +
            "^BH\\d{2}[A-Z]{4}[0-9A-Z]{14}$|" +
            "^BE\\d{14}$|" +
            "^BA\\d{18}$|" +
            "^BG\\d{2}[A-Z]{4}\\d{6}[0-9A-Z]{8}$|" +
            "^HR\\d{19}$|" +
            "^CY\\d{10}[0-9A-Z]{16}$|" +
            "^CZ\\d{22}$|" +
            "^DK\\d{16}$|" +
            "^FO\\d{16}$|" +
            "^GL\\d{16}$|" +
            "^DO\\d{2}[0-9A-Z]{4}\\d{20}$|" +
            "^EE\\d{18}$|" +
            "^FI\\d{16}$|" +
            "^FR\\d{12}[0-9A-Z]{11}\\d{2}$|" +
            "^GE\\d{2}[A-Z]{2}\\d{16}$|" +
            "^DE\\d{20}$|" +
            "^GI\\d{2}[A-Z]{4}[0-9A-Z]{15}$|" +
            "^GR\\d{9}[0-9A-Z]{16}$|" +
            "^HU\\d{26}$|^IS\\d{24}$|" +
            "^IE\\d{2}[A-Z]{4}\\d{14}$|" +
            "^IL\\d{21}$|" +
            "^IT\\d{2}[A-Z]\\d{10}[0-9A-Z]{12}$|" +
            "^[A-Z]{2}\\d{5}[0-9A-Z]{13}$|" +
            "^KW\\d{2}[A-Z]{4}22!$|" +
            "^LV\\d{2}[A-Z]{4}[0-9A-Z]{13}$|" +
            "^LB\\d{6}[0-9A-Z]{20}$|" +
            "^LI\\d{7}[0-9A-Z]{12}$|" +
            "^LT\\d{2}\\d{5}[0-9A-Z]{11}$|" +
            "^LU\\d{5}[0-9A-Z]{13}$|" +
            "^MK\\d{5}[0-9A-Z]{10}\\d{2}$|" +
            "^MT\\d{2}[A-Z]{4}\\d{5}[0-9A-Z]{18}$|" +
            "^MR13\\d{23}$|" +
            "^MU\\d{2}[A-Z]{4}\\d{19}[A-Z]{3}$|" +
            "^MC\\d{12}[0-9A-Z]{11}\\d{2}$|" +
            "^ME\\d{20}$|" +
            "^NL\\d{2}[A-Z]{4}\\d{10}$|" +
            "^NO\\d{13}$|" +
            "^PL\\d{10}[0-9A-Z]{16}n$|" +
            "^PT\\d{23}$|" +
            "^RO\\d{2}[A-Z]{4}[0-9A-Z]{16}$|" +
            "^SM\\d{2}[A-Z]\\d{10}[0-9A-Z]{12}$|" +
            "^SA\\d{4}[0-9A-Z]{18}$|" +
            "^RS\\d{20}$|" +
            "^SK\\d{22}$|" +
            "^SI\\d{17}$|" +
            "^ES\\d{22}$|" +
            "^SE\\d{22}$|" +
            "^CH\\d{7}[0-9A-Z]{12}$|" +
            "^TN59\\d{20}$|" +
            "^TR\\d{7}[0-9A-Z]{17}$|" +
            "^AE\\d{21}$|" +
            "^GB\\d{2}[A-Z]{4}\\d{14}");
}
