({
	baseUrl: './src',
	include: ['restCommunication/callXApi', 'restCommunication/callYApi'],
	optimize: 'none',
	out: 'compiled.js',
	skipSemiColonInsertion: true,
	onBuildWrite: function(id, path, contents) {
		contents = contents.replace(/define\((.|\s)*?\{/, '');
		contents = contents.replace(/\}\s*\)\s*;*\s*?.*$/, '');
		contents = contents.replace(/return(.|\s)*[^return]*$/, '');

		return contents;
	}
})