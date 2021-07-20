<template>
  <v-container>
    <acme-table/>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import AcmeTable from "@/components/AcmeTable.vue"; // @ is an alias to /src

@Component({
  components: {
    AcmeTable,
  },
})
export default class Home extends Vue {}
</script>
