define('B',[],function() {
	function B() {
		console.log('B');
	}
	
	return B;
});
define('C',['B'], function(B) {
    B();
    function C() {
        console.log('C');
    }
    
    return C;
});
define('A',['C'], function(C) {
	C();
	console.log('A');
});
