import { layoutReducer } from './layoutReducer';
import { produtorReducer } from './produtorReducer';
import { combineReducers } from 'redux';

export const Reducers = combineReducers(
    {
        layoutState: layoutReducer,
        produtorState: produtorReducer
    }
);
