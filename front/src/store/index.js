
import { createStore } from 'vuex';

export default createStore({
  state: {
    showDeleteModal: false,
    articleIdToDelete: null,
  },
  mutations: {
    SET_DELETE_MODAL(state, payload) {
      state.showDeleteModal = payload.show;
      state.articleIdToDelete = payload.articleId;
    },
  },
  actions: {
    openDeleteModal({ commit }, articleId) {
      commit('SET_DELETE_MODAL', { show: true, articleId });
    },
  },
  getters: {
    getDeleteModalState: (state) => state.showDeleteModal,
    getArticleIdToDelete: (state) => state.articleIdToDelete,
  },
});
