import requests
import time
import statistics

def make_get_request(url):
    start_time = time.time()  # Registro del tiempo inicial
    response = requests.get(url)
    elapsed_time = time.time() - start_time  # Cálculo del tiempo transcurrido
    return response, elapsed_time

def main():
    api_url = "http://172.31.65.158:8094/reports/client/transactions/detail?since=2021-11-11&until=2023-12-12&offset=10&sort=createdAt&filterDate=processedAt&take=50"  # Reemplaza esto con la URL de tu API
    response_times = []

    for i in range(10):
        response, elapsed_time = make_get_request(api_url)
        print(f"Request {i+1}: {response.status_code} ({elapsed_time} segundos)")
        response_times.append(elapsed_time)
        time.sleep(1)

    # Cálculo del promedio y la mediana
    average_time = sum(response_times) / len(response_times)
    median_time = statistics.median(response_times)

    print(f"\nPromedio de tiempo de respuesta: {average_time} segundos")
    print(f"Mediana de tiempo de respuesta: {median_time} segundos")

if __name__ == "__main__":
    main()