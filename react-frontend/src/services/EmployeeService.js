import axios from 'axios';

const EMPLOYEE_API_BASE_URL = "http://localhost:8080";

class EmployeeService {

    getEmployees() {
        return axios.get(EMPLOYEE_API_BASE_URL + '/list');
    }

    createEmployee(employee) {
        return axios.post(EMPLOYEE_API_BASE_URL + '/insert', employee);
    }

    getEmployeeById(employeeId) {
        return axios.get(EMPLOYEE_API_BASE_URL + '/list/' + employeeId);
    }

    updateEmployee(employee, employeeId) {
        return axios.put(EMPLOYEE_API_BASE_URL + '/update/' + employeeId, employee);
    }

    deleteEmployee(employeeId) {
        return axios.delete(EMPLOYEE_API_BASE_URL + '/delete/' + employeeId);
    }
}

export default new EmployeeService()