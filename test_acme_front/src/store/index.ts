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
    updateName(state, payload) {
      const index = state.colStores.findIndex((store) => store.id == payload.id);
      state.colStores[index].name = payload.name;
    },
  },
  actions: {
    getColStores({ commit }) {
      StoreService.getColStores().then((res) => {
        commit('getColStores', res.data);
      });
    },
    updateName({ commit }, payload) {
      StoreService.updateName(payload).then((res) => {
        if (res.status === 200) {
          commit('updateName', payload);
        }
      });
    },
  },
  modules: {
  },
});
