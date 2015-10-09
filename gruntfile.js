module.exports = function(grunt) {
	// Project configuration.
	grunt.initConfig({
		pkg : grunt.file.readJSON('package.json'),
		// jsmin
		uglify : {
			static_mappings : {
				files : [ {
					src : '<%= pkg.src %>/admin/js/admin-268xue.js',
					dest : '<%= pkg.target %>/admin/js/admin-268xue.js'
				}, {
					src : '<%= pkg.src %>/js/common/commonJs.js',
					dest : '<%= pkg.target %>/js/common/commonJs.js'
				}, {
					src : '<%= pkg.src %>/js/common/webutils.js',
					dest : '<%= pkg.target %>/js/common/webutils.js'
				}, {
					src : '<%= pkg.src %>/js/common/placeholder.js',
					dest : '<%= pkg.target %>/js/common/placeholder.js'
				} ]
			},
			dynamic_mappings : {
				files : [ {
					expand : true,
					cwd : '<%= pkg.src%>/',
					src : [ 'js/blog/*.js', 'js/discuss/*.js',
							'js/friend/*.js', 'js/header/*.js', 'js/home/*.js',
							'js/letter/*.js', 'js/phome/*.js',
							'js/search/*.js', 'js/suggest/*.js',
							'js/weibo/*.js' ],
					dest : '<%= pkg.target %>/',
					ext : '.js'
				} ]
			}
		},
		// cssmin
		cssmin : {
			dynamic_mappings : {
				files : [ {
					expand : true,
					cwd : '<%= pkg.src%>/',
					src : [ 'css/*.css' ],
					dest : '<%= pkg.target %>/',
					ext : '.css'
				} ]
			}
		},

		// htmlmin
		/*htmlmin : {
			jsp : {
				options : {
					removeComments : true,
					collapseWhitespace : true
				},
				files : [ {
					expand : true,
					cwd : 'src/main/webapp/WEB-INF/jsp/',
					src : [ 'blog/*.jsp', 'common/*.jsp', 'discuss/*.jsp',
							'friend/*.jsp', 'home/*.jsp', 'letter/*.jsp',
							'login/*.jsp', 'letter/*.jsp', 'phome/*.jsp',
							'search/*.jsp', 'suggest/*.jsp', 'weibo/*.jsp' ],
					dest : 'target/sns-web/WEB-INF/jsp/',
					ext : '.jsp'
				} ]
			},
			layouts : { 
				options : {
					removeComments : true,
					collapseWhitespace : true
				},
				files : [ {
					expand : true,
					cwd : 'src/main/webapp/WEB-INF/layouts/',
					src : [ '*.jsp' ],
					dest : 'target/sns-web/WEB-INF/layouts/',
					ext : '.jsp'
				} ]
			}
		}*/

	});

	// Load the plugin HTML/CSS/JS/IMG min
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-cssmin');
	//grunt.loadNpmTasks('grunt-contrib-htmlmin');

	// build task(s).grunt build
	grunt.registerTask('build', [ 'uglify', 'cssmin']);

};