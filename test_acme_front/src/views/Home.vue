<template>
  <acme-table :data="colStores" />
</template>

<script lang="ts">
import Vue from 'vue';
import AcmeTable from '../components/AcmeTable.vue';
import { mapActions, mapGetters } from 'vuex';
export default Vue.extend({
  name: 'Home',
  components: {
    AcmeTable,
  },
  computed: {
    ...mapGetters(['colStores']),
  },
  data() {
    return {
    };
  },
  methods: {
    ...mapActions(['getColStores']),
  },
  mounted() {
    this.getColStores();
  },
});
</script>
