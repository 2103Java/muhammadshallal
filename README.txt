Major dones:

1) (Change only on the backend side.) I think getEmployeeCount should be a method in finance managers services rather than employee services. I moved this method
from employee controller/service to finanial manager controller/service. Now, everything was there in the finance manager,
however the request helper was using the get employee count of the employee controller rather than the one in fin man controller.
I fixed this and cleaned it up. Now, getting employee
count is only a service for finance managers and it no longer exist in regular employee services.

2) (Change only on the backend side.) There are methods in FinancialManagerControllerImpl that interact with the database directly without going through
the sefrvice layer, this just violates the overall front controller design and will expose our team to pretty nasty and annoyinh
comments. I will fix this by having all these methods go through the service layer first
Now, all action follows this route: front end(js, AJAX) => servlets => requestHelper => controller (last to deal with http requests)
=> services (no more http requests, parameters are extracted from requests at the controller level) => jdbc/rds

3) (Change only on the backend side.) Commented out unregister method which can be used to allow fin managers to delete an employee along with its 
reimbursement requests from the system. We didn't implement this and hence I commented this out.

4) (unit tests) Employee services had unit tests written, passed 5 out of 5 unit tests.
5) (unit tests) FinancialManager services had unit tests written, passed 5 out of 5 unit tests but with a catch, I need teammate help
to look at the unit tests and help finding a better way of testing other than manually setting
up variables such as existing claim id for testing methods. Test must be automated and I don't know how to automate
these service tests given that other tests affect the test at hand. For example, I have to get an id of a pending
ticket from the db maually so as to test getStatusById.


6) (On the front end side), I am only going to try to raise the issue on the reimbursement table shown to finance managers
the goal is to delete the id column and have a copy id button only next to pending status
I am following this: https://www.w3schools.com/howto/howto_js_copy_clipboard.asp
I am only modifiyin the showReimbursmentsServlet doPost method,
I tried to add a js function called copyId in claims.html, it didn't bind or connect with the ticketId hidden field in the servlet
When I added the function in the servlet, the system still can't see it.

Could you add the function in claims.js and modify it to be AJAX based similar to the other js function?
You only need to check the showReimbursmentsServlet, I removed the column of an id and added the logic
of this copyId button and the hidden field in the column of status within the for loop

All you need to do is to make this copyId function reachable when the button is clicked
Nothing on the front end was modified, only this servlet is modified.
function copyId() {
		  var copyText = document.getElementById("ticketId");
		  copyText.select();
		  copyText.setSelectionRange(0, 99999)
		  document.execCommand("copy");
		  alert("Copied the id: " + copyText.value);
	}

I will include a screenshot of how this looks like.

Priorities:
1) finance manager should be able to view the receipt of a ticket, can we implement this given we can upload and save 
receipt images?
