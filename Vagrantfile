# -*- mode: ruby -*-
# vi: set ft=ruby :

$script = <<SCRIPT
echo I am provisioning...
date > /etc/vagrant_provisioned_at
SCRIPT

Vagrant.configure("2") do |config|
  config.vm.provision "shell", inline: $script

  config.vm.provider :virtualbox do |v|
    v.customize ["modifyvm", :id, "--memory", "2048"]
    v.customize ["modifyvm", :id, "--cpus", "2"]
  end

end

Vagrant::Config.run do |config|
  config.vm.box = "ubuntu/trusty64"
  config.vm.box_url = "https://atlas.hashicorp.com/ubuntu/boxes/trusty64"
  config.vm.host_name = "postgresql" 

  config.vm.share_folder "bootstrap", "/mnt/bootstrap", ".", :create => true
  config.vm.provision :shell, :path => "Vagrant-setup/bootstrap.sh"
  
  # PostgreSQL Server port forwarding
  config.vm.forward_port 5432, 15432
  # Graphite web interface
  config.vm.forward_port 443, 8443
  # Graphite stats interface
  config.vm.forward_port 2003, 20003
end
