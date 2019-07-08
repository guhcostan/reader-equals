import { FETCH_PRODUTOR, UPDATE_PRODUTOR } from '../actionTypes';

// TODO: Mockado
const INITIAL_STATE = {
    produtor: {}
};

export const produtorReducer = (state = INITIAL_STATE, action) => {
    switch (action.type) {
        case FETCH_PRODUTOR:
            return {
                ...state,
                produtor: action.produtor
            };

        case UPDATE_PRODUTOR:
            break;
        default:
            return state;
    }
};
