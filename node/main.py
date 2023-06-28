import requests
import os
from urllib.parse import urlparse, urljoin

def download_file(url, directory):
    # Send a GET request to the URL
    response = requests.get(url)
    
    # Extract the file name from the URL
    filename = os.path.join(directory, os.path.basename(urlparse(url).path))
    
    # Save the response content to a file
    with open(filename, 'wb') as file:
        file.write(response.content)
    
    print(f"Downloaded file: {filename}")

def clone_web_page(url, directory):
    # Send a GET request to the URL
    response = requests.get(url)
    
    # Create the directory if it doesn't exist
    if not os.path.exists(directory):
        os.makedirs(directory)
    
    # Extract the base URL to resolve relative links
    base_url = urljoin(url, '/')
    
    # Find all the links to external resources in the web page
    links = response.text.split('href="')[1:]
    for link in links:
        resource_url = urljoin(base_url, link.split('"')[0])
        download_file(resource_url, directory)
    
    # Find all the links to external images in the web page
    images = response.text.split('src="')[1:]
    for image in images:
        image_url = urljoin(base_url, image.split('"')[0])
        download_file(image_url, directory)
    
    print(f"Web page cloned successfully. Saved in directory: {directory}")

# Example usage
url = 'https://www.example.com'
directory = 'cloned_web_pages_2'

clone_web_page(url, directory)