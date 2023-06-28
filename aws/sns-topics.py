import boto3
import pandas as pd


def obtener_suscripciones():
    # Crear una instancia del cliente de SNS
    sns_client = boto3.client('sns')

    # Obtener la lista de tópicos
    response = sns_client.list_topics()
    

    topics = response['Topics']

    # Tabla para almacenar los datos
    tabla_suscripciones = []

    # Iterar sobre los tópicos
    for topic in topics:
        
        topic_arn = topic['TopicArn']
        topic_name = topic_arn.split(':')[-1]

        # Obtener las suscripciones para el tópico
        response = sns_client.list_subscriptions_by_topic(TopicArn=topic_arn)
        subscriptions = response['Subscriptions']

        # Agregar las suscripciones a la tabla
        for subscription in subscriptions:
            subscription_arn = subscription['SubscriptionArn']
            subscription_protocol = subscription['Protocol']
            subscription_endpoint = subscription['Endpoint']
            tabla_suscripciones.append({
                'Tópico': topic_name,
                'Protocolo': subscription_protocol,
                'Endpoint': subscription_endpoint
            })

    return tabla_suscripciones

# Obtener la tabla de suscripciones
tabla = obtener_suscripciones()

pd.set_option('display.max_rows', None)
df = pd.DataFrame(tabla)
# Mostrar el DataFrame
print(df)
df.to_csv('suscripciones.csv', sep='\t', index=False)
