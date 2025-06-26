function readDICOM(path)
    x = strsplit(path); %Get files paths
    %Show decrypted files
    for i=1:1:length(x)
        info = dicominfo(char(x(i))); %Read DICOM file by transforming the path specified in the current cell
        image = dicomread(info);
        figure
        imshow(image,[]);
    end
end