This commit is a major merger and represents itself to further improvement.

Major dones:
1) Employee can show only his/her all or pending or approved or denied or Lodging or Travel or Food or Other previous reimbursements
2) Finance managers can show all or pending or approved or denied or Lodging or Travel or Food or Other reimbursements for all employees

Priorities:
0) Use overloaded Reimbursment constructor to instantiate reimbursement objects retreived from the database to
show for either an employee or for a finance manager. Use it solely in the ReimbursmentRepositoryJdbc, modify the
table in the showReimbursmentsServlet so as to provide finance managers with all data they need
1) Allow finance managers to modify the status of a pending ticket
2) connect http session across pages
3) pass email between servlets after authentication
4) fix fileupload of receipt images
5) Front end needs serious focus

Pending Tasks:
1) Go through logging everything that needs to be logged
2) Unit testing of Controller and Services
