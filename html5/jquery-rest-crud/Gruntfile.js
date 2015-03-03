// Generated on 2015-03-03 using generator-angular 0.11.1
'use strict';

// # Globbing
// for performance reasons we're only matching one level down:
// 'test/spec/{,*/}*.js'
// use this if you want to recursively match all subfolders:
// 'test/spec/**/*.js'

var proxySnippet = require('grunt-connect-proxy/lib/utils').proxyRequest;

module.exports = function (grunt) {

	// Load grunt tasks automatically
	require('load-grunt-tasks')(grunt);

	// Time how long tasks take. Can help when optimizing build times
	require('time-grunt')(grunt);

	// Configurable paths for the application
	var appConfig = {
		app: require('./bower.json').appPath || 'app',
		dist: 'dist'
	};

	// Define the configuration for all the tasks
	grunt.initConfig({

		// Project settings
		workshop: appConfig,

		// Watches files for changes and runs tasks based on the changed files
		watch: {
			bower: {
				files: ['bower.json'],
				tasks: ['wiredep']
			},
			js: {
				files: ['<%= workshop.app %>/scripts/{,*/}*.js'],
				tasks: ['newer:jshint:all'],
				options: {
					livereload: '<%= connect.options.livereload %>'
				}
			},
			jsTest: {
				files: ['test/spec/{,*/}*.js'],
				tasks: ['newer:jshint:test', 'karma']
			},
			styles: {
				files: ['<%= workshop.app %>/styles/{,*/}*.css'],
				tasks: ['newer:copy:styles', 'autoprefixer']
			},
			gruntfile: {
				files: ['Gruntfile.js']
			},
			livereload: {
				options: {
					livereload: '<%= connect.options.livereload %>'
				},
				files: [
					'<%= workshop.app %>/{,*/}*.html',
					'.tmp/styles/{,*/}*.css',
					'<%= workshop.app %>/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}'
				]
			}
		},

		// The actual grunt server settings
		connect: {
			options: {
				port: 8081,
				// Change this to '0.0.0.0' to access the server from outside.
				hostname: 'localhost',
				livereload: 35729
			},
			proxies: [
			{
				context: '/',
				host: 'localhost',
				port: 8080
			}
			],
			livereload: {
				options: {
					open: true,
					base: [
						'.tmp',
						appConfig.app
					],
					middleware: function (connect) {
						// Setup the proxy
						var middlewares = [
							connect.static('.tmp'),
							connect().use( '/bower_components', connect.static('./bower_components')),
							connect().use('/app/styles', connect.static('./app/styles')),
							connect.static(appConfig.app),
							proxySnippet
						];

						return middlewares;
					}
				}
			},
			test: {
				options: {
					port: 8082,
					middleware: function (connect) {
						return [
							connect.static('.tmp'),
							connect.static('test'),
							connect().use('/bower_components', connect.static('./bower_components')),
							connect.static(appConfig.app)
						];
					}
				}
			},
			dist: {
				options: {
					open: true,
					base: '<%= workshop.dist %>'
				}
			}
		},

		// Make sure code styles are up to par and there are no obvious mistakes
		jshint: {
			options: {
				jshintrc: '.jshintrc',
				reporter: require('jshint-stylish')
			},
			all: {
				src: [
					'Gruntfile.js',
					'<%= workshop.app %>/scripts/{,*/}*.js'
				]
			},
			test: {
				options: {
					jshintrc: 'test/.jshintrc'
				},
				src: ['test/spec/{,*/}*.js']
			}
		},

		// Empties folders to start fresh
		clean: {
			dist: {
				files: [{
					dot: true,
					src: [
						'.tmp',
						'<%= workshop.dist %>/{,*/}*',
						'!<%= workshop.dist %>/.git{,*/}*'
					]
				}]
			},
			server: '.tmp'
		},

		// Add vendor prefixed styles
		autoprefixer: {
			options: {
				browsers: ['last 1 version']
			},
			server: {
				options: {
					map: true,
				},
				files: [{
					expand: true,
					cwd: '.tmp/styles/',
					src: '{,*/}*.css',
					dest: '.tmp/styles/'
				}]
			},
			dist: {
				files: [{
					expand: true,
					cwd: '.tmp/styles/',
					src: '{,*/}*.css',
					dest: '.tmp/styles/'
				}]
			}
		},

		// Automatically inject Bower components into the app
		wiredep: {
			app: {
				src: ['<%= workshop.app %>/index.html'],
				ignorePath:	/\.\.\//
			},
			test: {
				devDependencies: true,
				src: '<%= karma.unit.configFile %>',
				ignorePath:	/\.\.\//,
				fileTypes:{
					js: {
						block: /(([\s\t]*)\/{2}\s*?bower:\s*?(\S*))(\n|\r|.)*?(\/{2}\s*endbower)/gi,
							detect: {
								js: /'(.*\.js)'/gi
							},
							replace: {
								js: '\'{{filePath}}\','
							}
						}
					}
			}
		},

		// Renames files for browser caching purposes
		filerev: {
			dist: {
				src: [
					'<%= workshop.dist %>/scripts/{,*/}*.js',
					'<%= workshop.dist %>/styles/{,*/}*.css',
					'<%= workshop.dist %>/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}',
					'<%= workshop.dist %>/styles/fonts/*'
				]
			}
		},

		// Reads HTML for usemin blocks to enable smart builds that automatically
		// concat, minify and revision files. Creates configurations in memory so
		// additional tasks can operate on them
		useminPrepare: {
			html: '<%= workshop.app %>/index.html',
			options: {
				dest: '<%= workshop.dist %>',
				flow: {
					html: {
						steps: {
							js: ['concat', 'uglifyjs'],
							css: ['cssmin']
						},
						post: {}
					}
				}
			}
		},

		// Performs rewrites based on filerev and the useminPrepare configuration
		usemin: {
			html: ['<%= workshop.dist %>/{,*/}*.html'],
			css: ['<%= workshop.dist %>/styles/{,*/}*.css'],
			options: {
				assetsDirs: [
					'<%= workshop.dist %>',
					'<%= workshop.dist %>/images',
					'<%= workshop.dist %>/styles'
				]
			}
		},

		// The following *-min tasks will produce minified files in the dist folder
		// By default, your `index.html`'s <!-- Usemin block --> will take care of
		// minification. These next options are pre-configured if you do not wish
		// to use the Usemin blocks.
		// cssmin: {
		//	 dist: {
		//		 files: {
		//			 '<%= workshop.dist %>/styles/main.css': [
		//				 '.tmp/styles/{,*/}*.css'
		//			 ]
		//		 }
		//	 }
		// },
		// uglify: {
		//	 dist: {
		//		 files: {
		//			 '<%= workshop.dist %>/scripts/scripts.js': [
		//				 '<%= workshop.dist %>/scripts/scripts.js'
		//			 ]
		//		 }
		//	 }
		// },
		// concat: {
		//	 dist: {}
		// },

		imagemin: {
			dist: {
				files: [{
					expand: true,
					cwd: '<%= workshop.app %>/images',
					src: '{,*/}*.{png,jpg,jpeg,gif}',
					dest: '<%= workshop.dist %>/images'
				}]
			}
		},

		svgmin: {
			dist: {
				files: [{
					expand: true,
					cwd: '<%= workshop.app %>/images',
					src: '{,*/}*.svg',
					dest: '<%= workshop.dist %>/images'
				}]
			}
		},

		htmlmin: {
			dist: {
				options: {
					collapseWhitespace: true,
					conservativeCollapse: true,
					collapseBooleanAttributes: true,
					removeCommentsFromCDATA: true,
					removeOptionalTags: true
				},
				files: [{
					expand: true,
					cwd: '<%= workshop.dist %>',
					src: ['*.html', 'views/{,*/}*.html'],
					dest: '<%= workshop.dist %>'
				}]
			}
		},

		// ng-annotate tries to make the code safe for minification automatically
		// by using the Angular long form for dependency injection.
		ngAnnotate: {
			dist: {
				files: [{
					expand: true,
					cwd: '.tmp/concat/scripts',
					src: '*.js',
					dest: '.tmp/concat/scripts'
				}]
			}
		},

		// Replace Google CDN references
		cdnify: {
			dist: {
				html: ['<%= workshop.dist %>/*.html']
			}
		},

		// Copies remaining files to places other tasks can use
		copy: {
			dist: {
				files: [{
					expand: true,
					dot: true,
					cwd: '<%= workshop.app %>',
					dest: '<%= workshop.dist %>',
					src: [
						'*.{ico,png,txt}',
						'.htaccess',
						'*.html',
						'views/{,*/}*.html',
						'images/{,*/}*.{webp}',
						'styles/fonts/{,*/}*.*'
					]
				}, {
					expand: true,
					cwd: '.tmp/images',
					dest: '<%= workshop.dist %>/images',
					src: ['generated/*']
				}, {
					expand: true,
					cwd: 'bower_components/bootstrap/dist',
					src: 'fonts/*',
					dest: '<%= workshop.dist %>'
				}]
			},
			styles: {
				expand: true,
				cwd: '<%= workshop.app %>/styles',
				dest: '.tmp/styles/',
				src: '{,*/}*.css'
			}
		},

		// Run some tasks in parallel to speed up the build process
		concurrent: {
			server: [
				'copy:styles'
			],
			test: [
				'copy:styles'
			],
			dist: [
				'copy:styles',
				'imagemin',
				'svgmin'
			]
		},

		// Test settings
		karma: {
			unit: {
				configFile: 'test/karma.conf.js',
				singleRun: true
			}
		}
	});


	grunt.registerTask('serve', 'Compile then start a connect web server', function (target) {
		if (target === 'dist') {
			return grunt.task.run(['build', 'connect:dist:keepalive']);
		}

		grunt.task.run([
			'clean:server',
			'wiredep',
			'concurrent:server',
			'autoprefixer:server',
			'configureProxies',
			'connect:livereload',
			'watch'
		]);
	});

	grunt.registerTask('server', 'DEPRECATED TASK. Use the "serve" task instead', function (target) {
		grunt.log.warn('The `server` task has been deprecated. Use `grunt serve` to start a server.');
		grunt.task.run(['serve:' + target]);
	});

	grunt.registerTask('test', [
		'clean:server',
		'wiredep',
		'concurrent:test',
		'autoprefixer',
		'connect:test',
		'karma'
	]);

	grunt.registerTask('build', [
		'clean:dist',
		'wiredep',
		'useminPrepare',
		'concurrent:dist',
		'autoprefixer',
		'concat',
		'ngAnnotate',
		'copy:dist',
		'cdnify',
		'cssmin',
		'uglify',
		'filerev',
		'usemin',
		'htmlmin'
	]);

	grunt.registerTask('default', [
		'newer:jshint',
		'test',
		'build'
	]);
};
