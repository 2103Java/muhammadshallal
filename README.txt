This commit is a major merger and represents itself to further improvement.

Major dones:
1) Overloaded Reimbursment constructor used appropriagely to retreive data from rds
2) http session is connected across services so that authenticated user no longer needs to submit their credentials once they are logged in
3) logout is implemented using session.invalidate()
4) Finished logging info and debug on all the following levels: frontController & services & repositoryies
5) Unecessary files/classes were removed from the project, some classes/servlets were renamed for the sake of consitency and
ease of navigation

Priorities:
1) Allow finance managers to modify the status of a pending ticket
2) fix fileupload of receipt images
3) Front end needs serious focus

Pending Tasks:
1) Unit testing of Controller and Services
