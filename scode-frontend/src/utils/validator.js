import * as R from 'ramda';
import notifier from './notifier';

const validatorRules = {

    isNotEmpty(field, fieldName) {
        if (R.isEmpty(field.value)) {
            notifier.error(`Please enter ${fieldName}`);
            field.ref.focus();
            return false;
        }

        return true;
    },

    isEmail(field, fieldName) {
        if (!/\S+@\S+\.\S+/.test(field.value)) {
            notifier.error(`Invalid email format invalid`);
            field.ref.focus();
            return false;
        }

        return true;
    },

    isLengthBiggerThan(field, fieldName, maxLength) {
        if (field.value.length < maxLength) {
            notifier.error(`The ${fieldName} must more than ${maxLength} letters`);
            field.ref.focus();
            return false;
        }

        return true;
    },

}

const Validator = {

    validate(data, rules) {
        for(const [fieldName, ruleText] of Object.entries(rules)) {
            for(let rule of ruleText.split('|')) {
                let [ruleName, ruleArguments] = rule.split(':');

                // Ensure rule arguments is array not undefined
                ruleArguments = ruleArguments?.split(',') ?? [];

                // Stop the validation when current validator rule failed
                const result = validatorRules[ruleName].call(
                    this, data[fieldName], fieldName, ...ruleArguments
                );

                if (!result) {
                    return false;
                }
            }
        }

        return true;
    },

    passes(data, rules) {
        return this.validate(data, rules);
    },

    fails(data, rules) {
        return !this.passes(data, rules);
    },

}

export default Validator;
