import ReactDOM from "react-dom";
import React, { PureComponent } from "react";
import ReactCrop from "react-image-crop";
import "react-image-crop/dist/ReactCrop.css";
import "./App.css";

class App extends PureComponent {
    constructor(props) {
        super(props);
        this.state = {
            isClassModalVisible: true,
            image_classes: ["class1","class2","class3"],
            src: null,
            crop: {
                unit: '%',
                width: 50,
                height: 50,
                x: 25,
                y: 25
            }
          }
        this.showCrop=this.showCrop.bind(this);
    }

  onSelectFile = e => {
    if (e.target.files && e.target.files.length > 0) {
      const reader = new FileReader();
      reader.addEventListener("load", () =>
        this.setState({ src: reader.result })
      );
      reader.readAsDataURL(e.target.files[0]);
    }
  };

  // If you setState the crop in here you should return false.
  onImageLoaded = image => {
    this.imageRef = image;
  };

  onCropComplete = crop => {
    this.makeClientCrop(crop);
    //this.showCrop();
  };

  onCropChange = (crop, percentCrop) => {
    // You could also use percentCrop:
    // this.setState({ crop: percentCrop });
    this.setState({ crop });
  };

  async makeClientCrop(crop) {
    if (this.imageRef && crop.width && crop.height) {
      const croppedImageUrl = await this.getCroppedImg(
        this.imageRef,
        crop,
        "newFile.jpeg"
      );
      this.setState({ croppedImageUrl });
    }
  }

  getCroppedImg(image, crop, fileName) {
    const canvas = document.createElement("canvas");
    const scaleX = image.naturalWidth / image.width;
    const scaleY = image.naturalHeight / image.height;
    canvas.width = crop.width;
    canvas.height = crop.height;
    const ctx = canvas.getContext("2d");

    ctx.drawImage(
      image,
      crop.x * scaleX,
      crop.y * scaleY,
      crop.width * scaleX,
      crop.height * scaleY,
      0,
      0,
      crop.width,
      crop.height
    );

    return new Promise((resolve, reject) => {
      canvas.toBlob(blob => {
        if (!blob) {
          //reject(new Error('Canvas is empty'));
          console.error("Canvas is empty");
          return;
        }
        blob.name = fileName;
        window.URL.revokeObjectURL(this.fileUrl);
        this.fileUrl = window.URL.createObjectURL(blob);
        resolve(this.fileUrl);
      }, "image/jpeg");
    });
  }

  showCrop() {
      let x = this.state.crop.x;
      let xm = this.state.crop.x + this.state.crop.width;
      let y = this.state.crop.y;
      let ym = this.state.crop.y + this.state.crop.height;
      alert("x:"+ x + " xm:"+xm+" y:"+y+" ym:"+ym);
      //this.onOpenClassModal();
  }


  render() {
    const { crop, croppedImageUrl, src } = this.state;

    const imageClasses = this.state.image_classes.map(function(image_class,i) { 
        return(   
            <div key={i}>
                <button onClick={this.showCrop}> {image_class}</button> 
            </div>  
        ) 
      }, this); 

    return (
      <div className="App">
        <div>
          <input type="file" onChange={this.onSelectFile} />
        </div>
        <div>
            <button onClick={this.showCrop} > get Crop</button>
            <input type="text" id="type"></input>
            {imageClasses}
        </div>
        

        <div>
            {src && (
            <ReactCrop
                src={src}
                crop={crop}
                onImageLoaded={this.onImageLoaded}
                onComplete={this.onCropComplete}
                onChange={this.onCropChange}
            />
            )}
            {croppedImageUrl && (
            <img alt="Crop" style={{ maxWidth: "100%" }} src={croppedImageUrl} />
            )}
        </div>
        
      </div>
    );
  }
}

ReactDOM.render(<App />, document.getElementById("root"));