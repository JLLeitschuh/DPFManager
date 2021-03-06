Summary: DPF Manager
Name: dpf-manager
Version: 3.5.1
Release: 1
License: Unknown
Vendor: DPF Manager
Prefix: /opt
Provides: dpf-manager
Requires: ld-linux.so.2 libX11.so.6 libXext.so.6 libXi.so.6 libXrender.so.1 libXtst.so.6 libasound.so.2 libc.so.6 libdl.so.2 libgcc_s.so.1 libm.so.6 libpthread.so.0 libthread_db.so.1
Autoprov: 0
Autoreq: 0

#avoid ARCH subfolder
%define _rpmfilename %%{NAME}-%%{VERSION}-%%{RELEASE}.%%{ARCH}.rpm

#comment line below to enable effective jar compression
#it could easily get your package size from 40 to 15Mb but 
#build time will substantially increase and it may require unpack200/system java to install
%define __jar_repack %{nil}

%description
DPF Manager

%prep

%build

%install
rm -rf %{buildroot}
mkdir -p %{buildroot}/opt
cp -r %{_sourcedir}/DPFManager %{buildroot}/opt

%files

/opt/DPFManager

%post


xdg-desktop-menu install --novendor /opt/DPFManager/DPFManager.desktop

if [ "false" = "true" ]; then
    cp /opt/DPFManager/dpf-manager.init /etc/init.d/dpf-manager
    if [ -x "/etc/init.d/dpf-manager" ]; then
        /sbin/chkconfig --add dpf-manager
        if [ "false" = "true" ]; then
            /etc/init.d/dpf-manager start
        fi
    fi
fi

ln -s /opt/DPFManager/DPFManager /usr/bin/dpf-manager

%preun

xdg-desktop-menu uninstall --novendor /opt/DPFManager/DPFManager.desktop

if [ "false" = "true" ]; then
    if [ -x "/etc/init.d/dpf-manager" ]; then
        if [ "true" = "true" ]; then
            /etc/init.d/dpf-manager stop
        fi
        /sbin/chkconfig --del dpf-manager
        rm -f /etc/init.d/dpf-manager
    fi
fi

%postun

rm /usr/bin/dpf-manager

%clean