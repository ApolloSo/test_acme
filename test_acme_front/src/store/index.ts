import Vue from 'vue';
import Vuex from 'vuex';
import { StoreService } from '@/apis';
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    colStores: [],
    counts: 0,
  },
  getters: {
    colStores: (state) => state.colStores,
    counts: (state) => state.counts,
  },
  mutations: {
    getColStores(state, payload) {
      state.colStores = payload;
    },
    updateName(state, payload) {
      const index = state.colStores.findIndex((store) => store.id == payload.id);
      state.colStores[index].name = payload.name;
    },
    getCounts(state, payload) {
      state.counts = payload;
    },
  },
  actions: {
    getColStores({ commit }, payload= 0) {
      StoreService.getColStores(payload).then((res) => {
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
    getCounts({ commit }) {
      StoreService.getCounts().then((res) => {
        commit('getCounts', res.data);
      });
    },
  },
  modules: {
  },
});
