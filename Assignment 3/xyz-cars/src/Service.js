import axios from 'axios';

const CAR_API_BASE_URL = 'http://localhost:8080/api/';

class Service{
    getCars() {
        return axios.get(CAR_API_BASE_URL+"cars");
      }
    login(){
      return axios.post(CAR_API_BASE_URL+"users/login")
    }
    sendMessage(data) {
      return axios.post(CAR_API_BASE_URL+"webhook/message/ADI",{text :data})
    }
}
  
export default new Service();