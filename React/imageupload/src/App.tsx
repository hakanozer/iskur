import React, { useEffect, useState } from 'react';
import Dropzone, { defaultClassNames, IDropzoneProps, IFileWithMeta, ILayoutProps, StatusValue } from 'react-dropzone-uploader'

function App() {

  const Layout = ({ input, previews, submitButton, dropzoneProps, files, extra: { maxFiles } }: ILayoutProps) => {
    return (
      <div>
        {previews}
        <div {...dropzoneProps}>{files.length < maxFiles && input}</div>
        {files.length > 0 && submitButton}
      </div>
    )
  }


    const getUploadParams: IDropzoneProps['getUploadParams'] = () => ({
       url: 'https://httpbin.org/post' 
      })
  
    const handleSubmit: IDropzoneProps['onSubmit'] = (files, allFiles) => {
      console.log(files.map(f => f.meta))
      allFiles.forEach(f => {
        console.log("asds")
        f.remove();
      })
    }

    const handleChangeStatus = ( meta: IFileWithMeta, status:StatusValue) => {
      //console.log(status, meta)
    }
    
  return (
    <>
      <Dropzone
        getUploadParams={getUploadParams}
        LayoutComponent={Layout}
        onSubmit={handleSubmit}
        classNames={{ inputLabelWithFiles: defaultClassNames.inputLabel }}
        inputContent="Drop Files (Custom Layout)"
        onChangeStatus={handleChangeStatus}
        
      />
    </>
  );
}

export default App;
