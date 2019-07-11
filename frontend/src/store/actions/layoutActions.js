import { NEXT_STEP, PREV_STEP } from '../actionTypes';

export const prevStep = () => {
    return {
        type: PREV_STEP
    }
};

export const nextStep = () => {
    return {
        type: NEXT_STEP
    }
};
