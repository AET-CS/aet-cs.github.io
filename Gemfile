source "https://rubygems.org"

# This will help ensure the proper Jekyll version is running.
gem "jekyll", "~> 3.10.0"

# Windows and JRuby does not include zoneinfo files, so bundle the tzinfo-data gem
# and associated library.
platforms :mingw, :x64_mingw, :mswin, :jruby do
  gem "tzinfo", ">= 1", "< 3"
  gem "tzinfo-data"
end

# Performance-booster for watching directories on Windows
gem "wdm", "~> 0.1.1", :platforms => [:mingw, :x64_mingw, :mswin]

# Lock `http_parser.rb` gem to `v0.6.x` on JRuby builds since newer versions of the gem
# do not have a Java counterpart.
gem "http_parser.rb", "~> 0.6.0", :platforms => [:jruby]

# Plugins
group :jekyll_plugins do
  gem "jekyll-paginate", "~> 1.1"
  gem "jekyll-sitemap", "~> 1.4"
  gem "jekyll-gist", "~> 1.5"
  gem "jekyll-feed", "~> 0.17"
  gem "jekyll-include-cache", "~> 0.2"
  gem "jemoji", "~> 0.13"
  gem 'webrick' # Required for Ruby >= 3.0
end

# GitHub Pages compatible gems
gem "github-pages", group: :jekyll_plugins