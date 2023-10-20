import os
from flask import Flask,request
try:
    import vk_api
except:
    os.system("pip install --user vk_api")
    import vk_api
token = ''
vk_session = vk_api.VkApi(token=token)
vk = vk_session.get_api()
app = Flask(__name__)

@app.route('/vk',methods=['POST','GET'])
def go_vk():
    msg = request.args.get('msg')
    try:
        vk.messages.send(user_id=449965017,random_id=0,message=msg)
        return "Отправлено"
    except Exception as e:
        return f"Произошла ошибка: {e}"


@app.route('/get')
def return_json():
    try:
        f = open("/home/dadadada123/mysite/base.json",'r')
        x = f.read()
        f.close()
        return x
    except Exception as e:
        return e
