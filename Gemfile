source "https://rubygems.org"

# Use github-pages gem to match GitHub's environment exactly
gem "github-pages", group: :jekyll_plugins

# Theme (already included in github-pages, but needed for local theme override)
gem "minimal-mistakes-jekyll"

# Additional gems for functionality
gem "webrick", "~> 1.8"

# Windows and JRuby does not include zoneinfo files
platforms :mingw, :x64_mingw, :mswin, :jruby do
  gem "tzinfo", ">= 1", "< 3"
  gem "tzinfo-data"
end

# Performance-booster for watching directories on Windows
gem "wdm", "~> 0.1.1", :platforms => [:mingw, :x64_mingw, :mswin]