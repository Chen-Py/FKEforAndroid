from django.shortcuts import render

# Create your views here.
from django.http import HttpResponse
from FKEServer.ServerBase.Server import ServerBase

def index(request, a = "1", b = "2"):
    c = a + b;
    res = str(a) + ' + ' + str(b) + ' = ' + str(c)
    return HttpResponse(res)

def newUser(request, name = 'UserName', N = 0, m = 0, P = 0):
    private_code = (N, m, P)
    SB = ServerBase();
    return HttpResponse(SB.newUser(name, private_code))

def getUser(request, name = 'UserName'):
    SB = ServerBase();
    return HttpResponse(str(SB.getUser(name)))
