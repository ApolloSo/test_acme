<template>
  <v-container fluid>
    <v-data-table :headers="headers" :items="data" hide-default-footer="false">
      <template v-slot:item.name="{ item }">
        <div class="d-flex">
          <span v-if="itemToEdit != item.id">{{ item.name }}</span>
          <v-text-field
            @keyup.enter="submit(item.id, item.name)"
            v-if="itemToEdit == item.id"
            v-model="item.name"
            dense
          ></v-text-field>
          <v-icon
            v-if="itemToEdit != item.id"
            @click="itemToEdit = item.id"
            small
            >mdi-lead-pencil</v-icon
          >
        </div>
      </template>
      <template v-slot:item.description="{ item }">
        {{
          item.description.length > 30
            ? `${item.description.slice(0, 30)}...`
            : item.description
        }}
      </template>
    </v-data-table>
    <v-row class="align-center justify-end ma-6 text-caption">
      <span class="mx-2">
        {{ currentPage * 10 + 1 }}-{{ (currentPage + 1) * 10 }} of
        {{ counts }}
      </span>
      <v-icon class="mx-2" @click="prevPage">mdi-skip-previous</v-icon>
      <v-icon class="mx-2" @click="nextPage">mdi-skip-next</v-icon>
    </v-row>
  </v-container>
</template>
<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';
import { mapActions, mapGetters } from 'vuex';
@Component({
  computed: {
    ...mapGetters(['counts']),
  },
  methods: {
    ...mapActions(['updateName', 'getCounts', 'getColStores']),
  },
  mounted() {
    this.getCounts();
  },
})
export default class AcmeTable extends Vue {
  private headers: any[] = [
    { text: 'ID', value: 'id' },
    {
      text: 'Name',
      align: 'start',
      sortable: false,
      value: 'name',
      width: '200',
    },
    { text: 'Code', value: 'code' },
    { text: 'Opening date', value: 'opening_date' },
    { text: 'Store type', value: 'store_type' },
    { text: 'Season', value: 'season' },
    { text: 'Special f1', value: 'special_f1' },
    { text: 'Special f2', value: 'special_f2' },
    { text: 'Description', value: 'description', width: '100' },
  ];
  private currentPage: number = 0;
  private itemToEdit: string = '';
  @Prop() private data!: any[];
  public submit(id: string, name: string): void {
    this.itemToEdit = '';
    this.updateName({ id, name });
  }
  private prevPage(): void {
    if (this.currentPage !== 0) {
      this.currentPage -= 1;
      this.getColStores(this.currentPage);
    }
  }
  private nextPage(): void {
    if (this.currentPage * 10 < this.counts) {
      this.currentPage += 1;
      this.getColStores(this.currentPage);
    }
  }
}
</script>