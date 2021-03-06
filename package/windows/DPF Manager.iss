; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{0D5B5F16-9992-49DC-AD65-D66FE249CE67}
AppName=DPF Manager
AppVersion=3.5.1
AppVerName=DPF Manager {#SetupSetting("AppVersion")}
AppPublisher=DPF Manager
AppComments=DPF Manager
AppCopyright=Copyright (C) 2015-2016
AppPublisherURL=http://dpfmanager.org/
AppSupportURL=http://dpfmanager.org/
AppUpdatesURL=http://dpfmanager.org/
UsePreviousAppDir=no
;DefaultDirName={localappdata}\DPF Manager
DefaultDirName={pf}\DPF Manager
DefaultGroupName=DPF Manager
OutputBaseFilename=DPF Manager-{#SetupSetting("AppVersion")}
Compression=lzma
SolidCompression=yes
PrivilegesRequired=admin
SetupIconFile=DPF Manager\DPF Manager.ico
UninstallDisplayIcon={app}\DPF Manager.ico
UninstallDisplayName=DPF Manager
ArchitecturesInstallIn64BitMode=x64
ChangesEnvironment=yes

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked
Name: modifypath; Description: "Add application directory to your system path"

[Files]
Source: "DPF Manager\DPF Manager.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "DPF Manager\app\dpf-manager-console.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "DPF Manager\*"; DestDir: "{app}"; Excludes: "*.exe,resources"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "DPF Manager\app\resources\*"; DestDir: "{app}"; Excludes: "*.dpf,*.iss"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "DPF Manager\app\resources\*.dpf"; DestDir: "{%HOMEPATH}\DPF Manager"; Flags: ignoreversion

[Icons]
Name: "{group}\DPF Manager"; Filename: "{app}\DPF Manager.exe"
Name: "{commondesktop}\DPF Manager"; Filename: "{app}\DPF Manager.exe"; Tasks: desktopicon

[Run]
Filename: "{app}\DPF Manager.exe"; Description: "{cm:LaunchProgram,DPFManager}"; Flags: nowait postinstall skipifsilent

[UninstallDelete]
Type: dirifempty; Name: "{pf}\DPF Manager"

[Code]
const
	ModPathName = 'modifypath';
	ModPathType = 'user';

Procedure customUninstall();
var
  mRes : integer;
begin
  // Ask for delete DPF Manager folder
  mRes := MsgBox('Do you want to remove also reports and configuration files?', mbConfirmation, MB_YESNO or MB_DEFBUTTON2)
  // Line breaks with with ' #13#13 '
  if mRes = IDYES then begin
     DelTree(ExpandConstant('{%HOMEPATH}') + '\DPF Manager\', True, True, True);
  end;
end;

function ModPathDir(): TArrayOfString;
begin
	setArrayLength(Result, 1)
	Result[0] := ExpandConstant('{app}');
end;
// Fixed for javapackager
#include "DPF Manager\app\resources\modpath.iss"



