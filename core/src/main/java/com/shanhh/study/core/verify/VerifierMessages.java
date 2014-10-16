package com.shanhh.study.core.verify;

/**
 * The interface Verifier messages.
 * @author jack.zhang
 */
public interface VerifierMessages {

    /** The Constant MSG_INVALID_INPUTS. */
    String MSG_INVALID_INPUTS = "One or more inputs failed validation tests.";

    /** The postfix is required. */
    String POSTFIX_IS_REQUIRED = " is required.";

    /** The Constant POSTFIX_NO_NULLS_ALLOWED. */
    String POSTFIX_NO_NULLS_ALLOWED = " cannot be null.";

    /** The Constant POSTFIX_EMPTY_NOT_ALLOWED. */
    String POSTFIX_EMPTY_NOT_ALLOWED = " cannot be null or empty.";

    /** The Constant POSTFIX_NON_NEGATIVE. */
    String POSTFIX_NON_NEGATIVE = " cannot be negative.";

    /** The Constant POSTFIX_POSITIVE. */
    String POSTFIX_POSITIVE = " cannot be 0 or negative.";

    String POSTFIX_GREATER = " can not greater than %d.";

    /**
     * The POSTFIX_INVALID
     */
    String POSTFIX_INVALID  = " is invalid.";

    /**
     * The START_INDEX
     */
    String START_INDEX = "start_index";

    /**
     * The COUNT.
     */
    String COUNT = "count";

    String PAGE = "page";

    /**
     * The START_INDEX_ZERO_NEGATIVE
     */
    String START_INDEX_ZERO_NEGATIVE = START_INDEX + POSTFIX_POSITIVE;

    /**
     * The PAGE_ZERO_NEGATIVE
     */
    String PAGE_ZERO_NEGATIVE = PAGE + POSTFIX_POSITIVE;

    /**
     * The COUNT_ZERO_NEGATIVE
     */
    String COUNT_ZERO_NEGATIVE = COUNT + POSTFIX_POSITIVE;

    /**
     * The COUNT_OVERFLOW
     */
    String COUNT_OVERFLOW = COUNT + POSTFIX_GREATER;

    /**
     * The USERID.
     */
    String USERID = "User ID";
    /**
     * The USERID_ZERO_NEGATIVE
     */
    String USERID_ZERO_NEGATIVE = USERID + POSTFIX_POSITIVE;

}
