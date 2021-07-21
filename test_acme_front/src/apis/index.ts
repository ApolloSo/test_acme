import endPoint from '@/config/endpoints';
import axios from 'axios';

export const StoreService = {
  async getColStores() {
    const res = await axios.get(endPoint.GET_COLSTORES_URL);
    return res;
  },
  async updateName(payload) {
    const res = await axios.post(endPoint.UPDATE_NAME_URL, payload);
    return res;
  },
};
