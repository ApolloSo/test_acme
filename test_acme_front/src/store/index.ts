import Vue from 'vue';
import Vuex from 'vuex';
import { StoreService } from '@/apis';
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    colStores: [],
  },
  getters: {
    colStores: (state) => state.colStores,
  },
  mutations: {
    getColStores(state, payload) {
      state.colStores = payload;
    },
  },
  actions: {
    getColStores({ commit }) {
      StoreService.getColStores().then((res) => {
        commit('getColStores', res.data);
      });
    },
  },
  modules: {
  },
});
