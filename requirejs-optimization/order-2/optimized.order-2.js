define('B',[],function() {
    console.log('B');
});
define('C',['B'], function(B) {
	console.log('C');
});
define('A',['C'], function(C) {
	console.log('A');
});
