This commit is a major merger and represents itself to further improvement.

Major dones:
1) Overloaded Reimbursment constructor used appropriagely to retreive data from rds
2) http session is connected across services so that authenticated user no longer needs to submit their credentials once they are logged in

Priorities:
1) Allow finance managers to modify the status of a pending ticket
2) Implement logout by terminating the http session using session.invalidate()
2) fix fileupload of receipt images
3) Front end needs serious focus

Pending Tasks:
1) Go through logging everything that needs to be logged
2) Unit testing of Controller and Services
