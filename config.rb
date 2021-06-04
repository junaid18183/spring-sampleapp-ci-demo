##############################################################################################
# GENERAL CONFIGURATION
##############################################################################################

# A machine-readable name for your project.
#  - HCAP DevSecOps is able to derive other default settings from your project's name.
set :application, -> { ENV.fetch('DOCKER_REPO', "sampleapp") }
#                      ^^
#                      Allow overriding the docker repository name, if the DOCKER_REPO
#                      environment variable is set.

# The type of application packaging / publishing to be handled.
set :application_type, 'docker'

##############################################################################################
# IMAGE BUILDING
##############################################################################################

# Declare the image build tool that you want to use.
#  - HCAP DevSecOps defaults to using docker as an image build tool, if you have a Dockerfile.
#  - HCAP DevSecOps can also use packer as an image build tool.
set :image_tool, :docker

# The source image (or "base" image") that this image should be built from.
#  - HCAP DevSecOps supports various methods of determining the source image,
#    either from environment variables or from manifest files.
#  - You can always replace the the below logic with your own implementation,
#    as long as your implementation returns a Hash of Hash(es), such as:
#
#      { 'base' => { 'image_ref' => 'myimage:latest' },
#        'another' => { 'image_ref' => 'otherimage:latest' } }
#
#    The reason for the multi-level hash is so that multi-base docker builds can be supported,
#    as well as automatic parsing of upstream image manifest JSON files.
#
set :source_image do
  # Highest precedence is a SOURCE_IMAGE env var specifying an image ref.
  # Next, try using an upstream image manifest JSON file specified by SOURCE_IMAGE_FILE.
  # Finally, just fall back on a default source image.
  image_ref = ENV['SOURCE_IMAGE']
  image_manifest = read_image_manifest(ENV['SOURCE_IMAGE_FILE']) if image_ref.nil?
  image_manifest || { 'base'  => { 'image_ref' => image_ref || "junaid18183/jre1.8:2.0.0" } }
end

# The full set of build variables to pass to the image builder.
set :image_build_vars, lambda {
  {
    SOURCE_IMAGE: fetch(:source_image)['base']['image_ref'],
    BUILD_URL: fetch(:build_url, 'None'),
    BUILD_DATE: fetch(:build_date),
    GIT_SHA: fetch(:git_commit)
  }
}

##############################################################################################
# IMAGE TESTING
##############################################################################################

# Specify which server testing tool to use.
set :server_test_tool, :inspec

# The test suite underneath the test/ subdirectory to execute.
set :test_suite, "sampleapp"

##############################################################################################
# IMAGE SCANNING
##############################################################################################

# Specify which image scanning security tool to use. The valid values are :blackduck and :none.
set :image_scan_tool, :none

##############################################################################################
# IMAGE PUBLISHING
##############################################################################################

# The types of image publishers to be used
set :image_publishers, %i[oci]
