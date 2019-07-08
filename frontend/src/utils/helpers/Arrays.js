export default class ArraysHelpers {

    static checkArray(array) {
        return array && Array.isArray(array) && array.length > 0;
    }
}