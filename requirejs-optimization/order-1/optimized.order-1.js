define('C',[],function() {
	console.log('C');
});
define('B',['C'], function(C) {
    console.log('B');
});
define('A',['B'], function(B) {
	console.log('A');
});
