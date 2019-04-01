package com.aimprosoft.sandbox.util.validator;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.configuration.annotation.BeanValidationAnnotationsConfigurer;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author BaLiK on 01.04.19
 */
public class OvalValidator {
    private static Logger LOG = Logger.getLogger(OvalValidator.class);

    private static OvalValidator instance = null;
    private net.sf.oval.Validator validator;

    private OvalValidator() {
        validator = new Validator(new AnnotationsConfigurer(), new BeanValidationAnnotationsConfigurer());
    }

    public static OvalValidator getInstance() {
        if (instance == null) {
            instance = new OvalValidator();
            LOG.info("Oval validator created");
        }

        return instance;
    }

    public boolean validate(Object object) {
        List<ConstraintViolation> violations = validator.validate(object);
        logViolations(violations);
        return violations.isEmpty();
    }

    private void logViolations(List<ConstraintViolation> violations) {
        if (violations.isEmpty()) {
            return;
        }

        for (ConstraintViolation violation : violations) {
            LOG.error("Oval validation error: " + violation.getMessage());
        }

    }

}