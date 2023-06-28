from flask import Flask

app = Flask(__name__)

@app.route('/stripe/payment/expired/<string:id>', methods=['PATCH'])
def expired_payment(id):
    return '', 200

if __name__ == '__main__':
    app.run(host='localhost', port=8497)