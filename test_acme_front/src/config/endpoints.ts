const baseUrl = process.env.VUE_APP_API;
const endpoints = {
  API_URL: baseUrl,
  GET_COLSTORES_URL: `${baseUrl}/api/collection_stores`,
  UPDATE_NAME_URL: `${baseUrl}/api/stores/update`,
  GET_COUNTS_URL: `${baseUrl}/api/collection_stores/getCount`,
};
export default endpoints;
