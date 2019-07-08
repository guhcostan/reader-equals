import { FETCH_PRODUTOR, UPDATE_PRODUTOR } from '../actionTypes';

export const fetchProdutor = (produtor) => {
    return {
        type: FETCH_PRODUTOR,
        produtor
    }
};

export const updateProdutor = (produtor) => {
    return {
        type: UPDATE_PRODUTOR,
        produtor
    }
};
