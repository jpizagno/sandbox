from aiohttp import web
import os

def index(request):
    message = "Welcome home! with processid = " + str(os.getpid())
    return web.Response(text=message)


my_web_app = web.Application()
my_web_app.router.add_route('GET', '/', index)

