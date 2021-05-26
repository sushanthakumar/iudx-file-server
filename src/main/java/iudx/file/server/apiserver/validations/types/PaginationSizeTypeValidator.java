package iudx.file.server.apiserver.validations.types;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaginationSizeTypeValidator implements Validator {

  private static final Logger LOGGER = LogManager.getLogger(PaginationSizeTypeValidator.class);

  private final String value;
  private final boolean required;

  public PaginationSizeTypeValidator(String value, boolean required) {
    this.value = value;
    this.required = required;
  }

  @Override
  public boolean isValid() {
    if (required && (value == null || value.isBlank())) {
      LOGGER.error("Validation error : null or blank value for required mandatory field");
      return false;
    } else {
      if (value == null || value.isBlank()) {
        return true;
      }
    }
    if (!isValidValue(value)) {
      LOGGER.error("Validation error : invalid pagination size Value [ " + value + " ]");
      return false;
    }
    return true;
  }

  private boolean isValidValue(String value) {
    try {
      int from = Integer.parseInt(value);
      if (from > 10000) {
        LOGGER.error("Validation error : invalid pagination size Value > 10000 [ " + value + " ]");
        return false;
      }
      return true;
    } catch (Exception ex) {
      LOGGER.error("Validation error : invalid pagination size Value [ " + value
          + " ] only integer expected");
      return false;
    }
  }

  @Override
  public int failureCode() {
    return 400;
  }

  @Override
  public String failureMessage() {
    return "bad query";
  }

}
