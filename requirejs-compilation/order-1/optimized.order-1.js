define('C',[],function() {
    function C() {
        console.log('C');
    }
    
    return C;
});
define('B',['C'], function(C) {
	C();
	function B() {
		console.log('B');
	}
	
	return B;
});
define('A',['B'], function(B) {
	B();
	console.log('A');
});
