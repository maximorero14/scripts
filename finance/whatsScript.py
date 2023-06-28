## python3 whatsScript.py input.txt
import sys
from os import path
from datetime import datetime, timedelta
import time
import os

file_name_read = './input.txt'

if (not path.isfile(file_name_read)):
	print("El archivo no existe.")
else:
	with open(file_name_read,'r+') as f:
		for line in f:
			line = line.strip('\n')
			date = line[1:9]
			
			messages = line[21:].split(":")
			
			if(len(messages) != 2):
				continue

			message = messages[1]
			messageSplit = message.split(" ")

			if(not messageSplit[1].isnumeric()):
				continue

			description = ''

			if(messageSplit[4] == 'max'):
				messageSplit[4] = 'maxi'

			try:
				description = ' '.join(messageSplit[5:])
			except Exception:
				pass

			messageFinal = "{0}###{1}###{2}###{3}###{4}###{5}".format(date, messageSplit[1], messageSplit[2], messageSplit[3], messageSplit[4], description)

			print(str(messageFinal))
