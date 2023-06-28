import boto3
import pandas as pd

def get_sns_topics():
    # Crear cliente de SNS
    sns_client = boto3.client('sns')
    
    # Obtener lista de tópicos
    topics = []
    next_token = None
    
    while True:
        params = {}
        if next_token:
            params['NextToken'] = next_token
            
        response = sns_client.list_topics(**params)
        topics.extend(response['Topics'])
        print(response['Topics'])
        if 'NextToken' in response:
            next_token = response['NextToken']
        else:
            break
    
    return topics

def get_sns_subscriptions(topic_arn):
    # Crear cliente de SNS
    sns_client = boto3.client('sns')
    
    # Obtener suscripciones por tópico
    response = sns_client.list_subscriptions_by_topic(
        TopicArn=topic_arn
    )
    
    subscriptions = response['Subscriptions']
    
    return subscriptions

# Obtener tópicos
topics = get_sns_topics()

# Crear una lista para almacenar los datos
data = []

# Iterar sobre cada tópico
for topic in topics:
    
    topic_arn = topic['TopicArn']
    print("maximorero14 topic " + topic_arn + "\n" )
    subscriptions = get_sns_subscriptions(topic_arn)
    
    # Obtener detalles de suscripciones
    for subscription in subscriptions:
        protocol = subscription['Protocol']
        endpoint = subscription['Endpoint']
        print("maximorero14 Endpoint " + endpoint + "\n" )
        # Agregar datos a la lista
        data.append({
            'TopicARN': topic_arn,
            'Protocol': protocol,
            'Endpoint': endpoint
        })

# Crear un DataFrame de pandas con los datos
df = pd.DataFrame(data)

# Imprimir el DataFrame
print(df)
df.to_csv('suscripciones2.csv', sep='\t', index=False)