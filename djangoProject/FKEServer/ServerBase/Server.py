from FKEServer.ServerBase.crypto import *
from FKEServer.ServerBase.usersdb import *
class ServerBase():
	def __init__(self):
		self.database = UsersDataBase()

	def newUser(self, name, private_code):
		addres = self.database.insert(name, private_code[0], private_code[1], private_code[2])
		self.database.close()
		if addres:
			return "ADD_SUCCESS"
		else:
			return "ADD_FAILED"

	def getUser(self, name):
		if not self.database.has(name):
			return "CHECK_FAILED"
		private_code = self.database.select(name)
		self.database.close()
		return private_code
