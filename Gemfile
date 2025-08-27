source "https://rubygems.org"

# Use Jekyll directly since we're not using GitHub's build environment
gem "jekyll", "~> 4.3.2"

# For remote themes
gem "jekyll-remote-theme"

# Essential plugins
gem "jekyll-paginate"
gem "jekyll-sitemap"
gem "jekyll-gist"
gem "jekyll-feed"
gem "jekyll-include-cache"
gem "jemoji"

# Additional gems for functionality
gem "webrick", "~> 1.8"
gem "kramdown-parser-gfm"

# Windows and JRuby does not include zoneinfo files
platforms :mingw, :x64_mingw, :mswin, :jruby do
  gem "tzinfo", ">= 1", "< 3"
  gem "tzinfo-data"
end

# Performance-booster for watching directories on Windows
gem "wdm", "~> 0.1.1", :platforms => [:mingw, :x64_mingw, :mswin]